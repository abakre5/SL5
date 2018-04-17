
#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"

int main( int argc, char **argv ){

	MPI_Status status;
	MPI_Comm client;

	char portName[MPI_MAX_PORT_NAME], ch, str[50];
	int size, i = 0, no, flag = 1;

	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Open_port(MPI_INFO_NULL, portName);
	printf("Server is running at port :: %s\n", portName);

	while(1){
		MPI_Comm_accept(portName, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &client);
		while(flag){
			MPI_Recv(&ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, client, &status);
			switch(status.MPI_TAG){
			case 0:
				MPI_Comm_free( &client ); 
	                        MPI_Close_port(portName); 
	                        MPI_Finalize(); 
				return 0;
				break;
			case 1:
				printf("Recieved Number :: %s\n", str);
				int no = atoi(str);
				i = no * no;
				printf("Square :: %d", i);
				flag = 0;
				MPI_Send(&i, 1, MPI_INT, 0, 2, client);
				break;
			case 2:
				str[i++] = ch;
				str[i] = '\0';
				break;
			}
		}
	}

	return 0;
}

