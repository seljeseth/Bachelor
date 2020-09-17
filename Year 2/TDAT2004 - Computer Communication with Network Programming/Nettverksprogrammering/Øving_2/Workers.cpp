//
// Created by Sabine Seljeseth on 22/02/2020.
//

#include "Workers.h"
#include <chrono>
#include <iostream>
#include <list>
#include <thread>



using namespace std::chrono;
using namespace std;

std::mutex print_mtx;



Workers::Workers(int nrOfWorkers)
{
	nrOfThreads = nrOfWorkers;
	finished = false;
}



//start the treads and tells them to execute the tasks in the task list
void Workers::start()
{
	for(int i = 0 ; i < nrOfThreads ; i++)
	{
		threads.emplace_back([&]
		                     {

			                     print_mtx.lock();
			                     cout << "Creating thread with ID: " << YELLOW << this_thread::get_id() << RESET
			                          << "\n" << endl;
			                     print_mtx.unlock();


			                     while(true)
			                     {
				                     function<void()> task;
				                     {

					                     unique_lock<mutex> lock(mtx);

					                     if(!tasks.empty())
					                     {
						                     task = *tasks.begin(); // Copy task for later use
						                     tasks.pop_front(); // Remove task from list
					                     }
					                     else
					                     {
						                     if(finished) return;
						                     cv.wait(lock);
					                     }
				                     }
				                     if(task)
					                     task(); // Run task outside of mutex lock
			                     }
		                     });
	}
}



//posts a new task to the task list
void Workers::post(const std::function<void()> &task)
{
	unique_lock<mutex> lock(mtx);
	tasks.push_back(task);
	cv.notify_one();
}



//Stops the treads when the task list is empty
void Workers::stop()
{

	{
		unique_lock<mutex> lock(mtx);
		finished = true;
	}
	cv.notify_all();

	for(auto &t : threads) t.join();
}



//adds a task to the list after x amount of ms
void Workers::post_timeout(const std::function<void()> &task , int time)
{
	post([task , time]
	     {
		     this_thread::sleep_for(milliseconds(time));
		     task();
	     });
}
