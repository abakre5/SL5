#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"

int main(int argc, char **argv) {

	MPI_Comm server;
	MPI_Status status;

	char portName[MPI_MAX_PORT_NAME], str[50], ch;
	int i;

	MPI_Init(&argc, &argv);
	strcpy(portName, argv[1]);
	MPI_Comm_connect(portName, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &server);

	printf("Enter String :: ");
	scanf("%s", str);

	for (i = 0; i < strlen(str); i++)
		MPI_Send(&str[i], 1, MPI_CHAR, 0, 2, server);
	MPI_Send(&i, 0, MPI_INT, 0, 1, server);

	i = 0;
	while (1) {
		MPI_Recv(&ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, server,	&status);
		if (status.MPI_TAG == 2) {
			str[i] = ch;
			i++;
		} else
			break;
	}

	printf("\nReversed String is %s\n", str);
	MPI_Comm_disconnect(&server);
	MPI_Finalize();

	return 0;
}
