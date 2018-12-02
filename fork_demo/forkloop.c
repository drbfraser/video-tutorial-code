#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{

	for (int i = 1; i <= 4; i++) {
		fork();
		printf("%d: My PID = %d\n", i, (int) getpid());
	}

	sleep(1);
	printf("--> My PID = %d\n", (int) getpid());

	return 0;
}
