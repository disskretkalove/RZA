
import mpi.*;
public class Main {
    public static void
    main(String[] args)
            throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int buf = rank;
        int s=0;
        int TAG = 0;
//        for (i = 0; i < size; i++) {
//            while(rank)
//            MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, rank + 1, TAG);
//        }
        while(rank!=size)
        {
            if (rank!=0 && rank!=5)
            {
                MPI.COMM_WORLD.Recv(buf,0,1,MPI.INT,MPI.ANY_SOURCE,TAG);
                s=buf+rank;
                buf=s;
                System.out.println(buf);
                MPI.COMM_WORLD.Send(buf,0,1,MPI.INT,rank+1,TAG);
                rank+=1;

            }
            else {
                MPI.COMM_WORLD.Recv(buf,0,1,MPI.INT,MPI.ANY_SOURCE,TAG);
                if(buf==0) {
                    MPI.COMM_WORLD.Send(buf, 0, 1, MPI.INT, rank + 1, TAG);
                    rank+=1;
                }
                else
                    if(rank==5)
                    {
                        MPI.COMM_WORLD.Recv(buf,0,1,MPI.INT,MPI.ANY_SOURCE,TAG);
                        s=buf+rank;
                        buf=s;
                        System.out.println(buf);
                        MPI.COMM_WORLD.Send(buf,0,1,MPI.INT,rank+1,TAG);
                        rank=0;
                    }
                    else
                    {
                        System.out.println("Сумма"+buf);
                        MPI.Finalize();
                    }
            }


        }
        MPI.Finalize();
    }
}