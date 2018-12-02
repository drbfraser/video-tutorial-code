#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

void doSomeWork(char *name)
{
	const int NUM_TIMES = 2;
	for (int i = 0; i < NUM_TIMES; i++) {
		sleep(rand() % 4);
		printf("Done pass %d for %s\n", i, name);
	}
}

int main(int argc, char *argv[])
{
	printf("I am: %d\n", (int) getpid());

	pid_t pid = fork();
	srand((int) pid);
	printf("fork returned: %d\n", (int) pid);

	if (pid < 0) {
		perror("Fork failed");
	}
	if (pid == 0) {
		printf("I am the child with pid %d\n", (int) getpid());
		doSomeWork("Child");
		exit(42);
	}

	// We must be the parent
	printf("I am the parent, waiting for child to end.\n");
	sleep(10);
	//doSomeWork("Parent");

	int status = 0;
	pid_t childpid = wait(&status);
	printf("Parent knows child %d finished with status %d.\n", (int)childpid, status);
	int childReturnValue = WEXITSTATUS(status);
	printf("   Return value was %d\n", childReturnValue);
	sleep(60);

	return 0;
}
