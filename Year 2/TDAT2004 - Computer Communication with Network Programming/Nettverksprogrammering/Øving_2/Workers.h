//
// Created by Sabine Seljeseth on 22/02/2020.
//

#ifndef NETTVERKSPROGRAMMERING_WORKERS_H
#define NETTVERKSPROGRAMMERING_WORKERS_H


#include <list>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <thread>





class Workers
{
private:


	int nrOfThreads;
	bool finished;
	std::vector<std::thread> threads;
	std::condition_variable cv;
	std::mutex mtx;
	std::list<std::function<void()>> tasks;



public:

	explicit Workers(int nrOfWorkers);

	void start();

	void post(const std::function<void()> &task);

	void stop();

	void post_timeout(const std::function<void()> &task , int time);
};





#endif //NETTVERKSPROGRAMMERING_WORKERS_H
