#include <iostream>
#include <thread>
#include <vector>
#include <chrono>

using namespace std;
using namespace std::chrono;
std::mutex mtx , printLock;

vector<int> primenumbers;
int total_nr_of_primes;



int checkStartValue(int start)
{
	int newStart = start;

	if(start <= 3)
	{

		cout << "\nPrime number 3 was found" << endl;
		primenumbers.push_back(3);
		total_nr_of_primes++;
		newStart = 5;

		if(start < 3)
		{
			cout << "Prime number 2 was found\n" << endl;
			primenumbers.push_back(2);
			total_nr_of_primes++;
			newStart = 5;
		}
	}
	if(start % 2 == 0)
	{
		newStart = start + 1;
	}
	return newStart;
}



int findPrime(int number)
{
	if(number > 1)
	{

		for(int i = 3 ; i * i <= number ; i++)
		{
			if(number % i == 0)
			{
				return 0;
			}
		}

		mtx.lock();
		primenumbers.push_back(number);
		total_nr_of_primes++;
		mtx.unlock();

		return number;
	}
}



void threadFunction(int start , int stop , int jump , int threadNr)
{
	int primes = 0;
	int numbers = 0;

	for(int i = start ; i <= stop ; i += jump)
	{
		/*printLock.lock();
		cout << "Thread " << threadNr << " checked: " << i << endl;
		printLock.unlock();*/
		numbers++;

		if(findPrime(i) != 0)
		{
			//cout << "Thread " << threadNr << " found prime: " << i << endl;
			primes++;
		}

		//counter++;
	}
	//cout << "  " << endl;
	cout <<  CYAN  << "Thread " << threadNr << " checked: "  << RESET << numbers << " and found " << primes << " "
																										   "primes\n"
	<<
	endl;

}



void findPrimesWithThreads(int start , int stop , int nrOfThreads)
{
	cout << YELLOW << nrOfThreads << " Thread(s)" << RESET << endl;

	//resetting the vector and counter
	primenumbers.clear();
	total_nr_of_primes = 0;

	int print_start = start;
	auto start_time = high_resolution_clock::now();
	thread threads[nrOfThreads];
	start = checkStartValue(start);
	int jump = nrOfThreads * 2;

	for(int i = 0 ; i < nrOfThreads ; i++)
	{
		threads[i] = thread(threadFunction , start , stop , jump , i + 1);
		start += 2;
	}

	for(auto &t : threads) t.join();

	auto stop_time = high_resolution_clock::now();
	auto duration = duration_cast<milliseconds>(stop_time - start_time);

	cout << "Time taken by function: "
	     << duration.count() << " ms\n"
	     << "To check from " << print_start << " to " << stop << " with " << nrOfThreads << " threads found: "
	     << GREEN << total_nr_of_primes << RESET << " primes\n\n" << "-------------------\n" << endl;
}



void print()
{
	printLock.lock();
	sort(primenumbers.begin() , primenumbers.end());
	for(int i = 0 ; i < primenumbers.size() ; i++)
	{
		cout << primenumbers.at(i) << " ";
	}
	printLock.unlock();
}



void main_øving1()
{
	int maxInt = 1410065408;
	int start = 1;
	int stop = 100;

	findPrimesWithThreads(start , stop, 1);
	findPrimesWithThreads(start , stop, 2);
	findPrimesWithThreads(start , stop, 3);
	findPrimesWithThreads(start , stop, 4);
	findPrimesWithThreads(start , stop, 5);
	findPrimesWithThreads(start , stop, 6);
	findPrimesWithThreads(start , stop, 7);
	/*findPrimesWithThreads(start , stop, 8);
	findPrimesWithThreads(start , stop, 9);
	findPrimesWithThreads(start , stop, 10);
	findPrimesWithThreads(start , stop, 11);*/


	//print();
}
//
// Created by Sabine Seljeseth on 31/01/2020.
//

