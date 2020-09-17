//
// Created by Sabine Seljeseth on 24/02/2020.
//
#include "Workers.cpp"



using namespace std;



void main_øving2()
{
	cout << "MAIN THREAD " << YELLOW << this_thread::get_id() << RESET << "\n" << endl;

	Workers worker_threads(4);
	Workers event_loop(1);


	string taskA = "task A";
	string taskB = "task B";
	string taskC = "task C";
	string taskD = "task D";
	string timeoutTask = "Timeout task";

	int timeA = 20;
	int timeB = 4000;
	int timeC = 5000;
	int timeOut = 2000;


	worker_threads.start(); // Create 4 internal threads
	event_loop.start();    // Create 1 internal thread
	worker_threads.post([taskA , timeA]
	                    {
		                    // Task A
		                    this_thread::sleep_for(milliseconds(timeA));
		                    cout << taskA << " done by " << YELLOW << this_thread::get_id() << RESET << " in " <<
		                         timeA
		                         << "ms" << BLUE
		                         << " "
		                            "(Worker "
		                            "Thread)" << RESET << endl;;
	                    });
	worker_threads.post([taskB , timeB]
	                    {
		                    this_thread::sleep_for(milliseconds(timeB));
		                    cout << taskB << " done by " << YELLOW << this_thread::get_id() << RESET << " in " << timeB
		                         << "ms" <<
		                         BLUE
		                         << " "
		                            "(Worker Thread) " << RESET << endl;
	                    });
	worker_threads.post([taskC , timeC]
	                    {

		                    // Task B
		                    // Might run in parallel with task A
		                    this_thread::sleep_for(milliseconds(timeC));
		                    cout << taskC << " done by " << YELLOW <<
		                         this_thread::get_id() << RESET << " in " << timeC
		                         << "ms" << BLUE << " (Worker Thread) " << RESET << endl;
	                    });
	event_loop.post_timeout([timeoutTask , timeOut]
	                        {
		                        cout << timeoutTask << " done after " << timeOut << "ms " << YELLOW <<
		                             this_thread::get_id() << RESET << BLUE << " (Event Loop) " << RESET << endl;
	                        } , 2000);
	event_loop.post([taskD]
	                {
		                cout << taskD << " done by " << YELLOW << this_thread::get_id() << RESET << BLUE << " (Event "
		                                                                                                    "Loop"
		                                                                                                    ") "
		                     << RESET << endl;
	                });


	worker_threads.stop();
	event_loop.stop();
};