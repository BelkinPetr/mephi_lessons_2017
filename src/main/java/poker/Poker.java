package main.java.poker;

/**
 * Created by Petr Belkin on 31.10.2017.
 */
public class Poker {

    public long C(long k, long n)
    {
        long k1=1;
        long l1=1;
        long n1=1;
        for (long i=1; i<=k; i++)
        {
            n1*=n;
            k1*=l1;
            l1++;
        }
        return n1/k1;
    }

    public double[][] flash_on_preflop()
    {
        double[][] matrix;
        matrix = new double[2][3];
        matrix[0][0] =C(2,13)*C(1,4);
        matrix[0][1] =(matrix[0][0]/C(2,52));
        matrix[0][2] =100*(double)(C(1,3)*C(5,13)+C(3,11)*C(2,47))/C(5,50);
        matrix[1][0] =C(2,52)-matrix[0][0];
        matrix[1][1] =(matrix[1][0]/C(2,52));
        matrix[1][2] =100* (double)(C(1,2)*C(5,13)+C(1,2)*C(4,12)*C(1,46))/C(5,50);
        return matrix;
    }

    public double[][] flash_flop_first_combine()
    {
        double[][] matrix;
        matrix = new double[3][3];
        matrix[0][0] =C(2,13)+ C(1,13)*C(1,13)+C(2,13)+C(1,13)*C(1,12)+C(1,13)*C(1,12); // при таком колличестве комбинаций собрать флеш нельзя
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49))); // колличество подходящих для флеша комбинаций при заданных картах П2,П3,К5,К10
        matrix[0][2] =0;
        matrix[1][0] =C(1,11)*C(1,38)+C(2,12); //дальний флеш-дро, на руках 3 одинаковых масти
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));//доля
        matrix[1][2] =100* (double)C(2,10)/C(2,47);//вероятность получить флеш при флопе
        matrix[2][0] =C(2,11);
        matrix[2][1] =matrix[2][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[2][2] =100* (double)(C(1,9)*C(1,46))/C(2,47);
        return matrix;
    }

    public double[][] flash_tern_first_combine()
    {
        double[][] matrix;
        matrix = new double[2][3];
        matrix[0][0] =C(1,2)*C(2,11);
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[0][2] =100* (double)C(1,9)/C(1,46);
        matrix[1][0] =(C(2,52)-(C(2,4)+C(1,4)*C(1,48)))-C(1,2)*C(2,11);
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[1][2] =0;
        return matrix;
    }

    public double[][] flash_flop_second_combine()
    {
        double[][] matrix;
        matrix = new double[2][3];
        matrix[0][0] =C(1,12)*C(1,12)+ C(1,12)*C(1,13) + C(1,12)*C(1,12)+C(1,12)*C(1,12)+C(1,12)*C(1,13)+C(1,12)*C(1,13)+C(2,13);
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[0][2] =0;
        matrix[1][0] =C(2,12)*C(1,3); //дальний флеш-дро
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[1][2] =100* (double)C(2,10)/C(2,47); //4 одномастных собрать уже не можем,только 2 варианта
        return matrix;
    }

    public double[][] flash_tern_second_combine()
    {
        double[][] matrix;
        matrix = new double[2][3];
        matrix[0][0] = C(2,11);
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[0][2] =100* (double)C(1,9)/C(1,46);
        matrix[1][0] =(C(2,52)-(C(2,4)+C(1,4)*C(1,48)))-C(2,11);
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[1][2] =0;
        return matrix;
    }

    public double[][] flash_flop_third_combine()
    {
        double[][] matrix;
        matrix = new double[3][3];
        matrix[0][0] =C(2,13)+C(1,13)*C(1,12)+ C(1,13)*C(1,13)+C(2,13)+C(1,12)*C(1,13);
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[0][2] =0;
        matrix[1][0] =C(1,11)*C(1,38)+C(2,12);
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[1][2] =100* (double)C(2,10)/C(2,47);
        matrix[2][0] =C(2,11);
        matrix[2][1] =matrix[2][0]/(C(2,52)-(C(2,3)+C(1,3)*C(1,49)));
        matrix[2][2] =100* (double)(C(1,9)*C(1,46))/C(2,47);
        return matrix;
    }

    public double[][] flash_tern_third_combine()
    {
        double[][] matrix;
        matrix = new double[3][3];
        matrix[0][0] =C(2,12)+C(1,13)*C(1,12)+C(1,12)*C(1,13)+C(1,13)*C(1,13)+C(2,13)+C(2,13);
        matrix[0][1] =matrix[0][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[0][2] =0;
        matrix[1][0] =C(1,10)*C(1,12)+C(1,10)*C(1,13)+C(1,10)*C(1,13);
        matrix[1][1] =matrix[1][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[1][2] =100* (double)C(1,9)/C(1,46);
        matrix[2][0] =C(2,10);
        matrix[2][1] =matrix[2][0]/(C(2,52)-(C(2,4)+C(1,4)*C(1,48)));
        matrix[2][2] =100;
        return matrix;
    }



    public static void main(String[] args) {
        Poker poker = new Poker();

        double flash_on_preflop[][] = poker.flash_on_preflop();
        System.out.println("Флеш на префлопе для всех комбинаций:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f", flash_on_preflop[i][j])+ "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_flop_first_combine[][] = poker.flash_flop_first_combine();
        System.out.println("Флеш на флопе для комбинации (П2,П3,К5,К10) :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f" ,flash_flop_first_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_tern_first_combine[][] = poker.flash_tern_first_combine();
        System.out.println("Флеш на терне для комбинации (П2,П3,К5,К10):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f" , flash_tern_first_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_flop_second_combine[][] = poker.flash_flop_second_combine();
        System.out.println("Флеш на флопе для комбинации (ПД,БВ,К5,К10) :");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f",flash_flop_second_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_tern_second_combine[][] = poker.flash_tern_second_combine();
        System.out.println("Флеш на терне для  комбинации (ПД,БВ,К5,К10):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f",flash_tern_second_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_flop_third_combine[][] = poker.flash_flop_third_combine();
        System.out.println("Флеш на флопе для комбинации (ПА,БА,ПК,П10) :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f",flash_flop_third_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        double flash_tern_third_combine[][] = poker.flash_tern_third_combine();
        System.out.println("Флеш на терне для комбинации (ПА,БА,ПК,П10):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(String.format( "%.2f",flash_tern_third_combine[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }
}