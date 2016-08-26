# threads
Programs on Java Threads - beginners to expert learning

Thread concepts and examples..
1. Volatile usage
2. Synchronized usage
3. Multiple locks
4. Thread Pool
5. Count down latch
6. Producer Consumer 
7. wait notify
8. Reentrant lock
9. Deadlock
10. Semaphore
11. Callable Future
12. Interruption

Notes:
-----
re-entrant lock = alternative for synchronized k/w

Deadlock solutions:
1. always lock your locks on the same order..
2. ReentrantLock() / Lock. tryLock()..

Semaphore
1. maintains the count
2. Seamphore(1) acts like lock / unlock
3. acquire and release can be from different threads - not like other locking mechanisms (lock / unlock should be from same thread).
4. to control accessing some resources..

Callable and Future
1. get return values from threads
2. allow to throw exceptions from thread code..

Interrupting thread
1. best way to interrupt is to have volatile boolean value
2. OR use interrupt method and check for the flag..
