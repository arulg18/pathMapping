public class TwelveNode {
    public enum node{
        crypto1,crypto2,balance1,balance2,relic;
    }
    public static int CRYPTO1 = 0;
    public static int CRYPTO2 = 1;
    public static int BALANCE1 = 2;
    public static int BALANCE2 = 3;
    public static int RELIC = 4;

    public int[][] coordinates = {
            {24,84},
            {36,24},
            {24,120},
            {24,48},
            {48,120}
    };
    public boolean[][]straight = {
            {true ,false,true ,true ,false},
                  {true ,false,true ,false},
                        {true ,true ,true },
                              {true ,false},
                                    {true }
    };


    public int[][] getCmd(int start, int stop){ //pass in constant ids
        int path[] = {start,stop};
        int j =1;
        for (int i = 0; i<2;i++) {
            if (straight[path[i]][path[j]])//if the path is straight
            {
                int[][]retval = {{i},coordinates[path[0]],coordinates[path[1]]};
                return retval;
            }
            else if(path[i]==CRYPTO1&& path[j]==CRYPTO2)
            {
                int[][] retval = {{i},coordinates[CRYPTO1],{36,72},coordinates[CRYPTO2]};
                return retval;
            }
            else if(path[i]==CRYPTO1&&path[j]==RELIC)
            {
                int[][] retval = {{i},coordinates[CRYPTO1],{48,120},coordinates[RELIC]};
                return retval;
            }
            else if(path[i]==CRYPTO2&&path[j]==BALANCE1)
            {
                int[][] retval = {{i},coordinates[CRYPTO2],{48,48},{24,84},coordinates[BALANCE1]};
                return retval;
            }
            else if(path[i]==CRYPTO2&&path[j]==RELIC)
            {
                int[][] retval = {{i},coordinates[CRYPTO2],{36,96},coordinates[RELIC]};
                return retval;
            }
            else if(path[i]==BALANCE2&&path[j]==RELIC)
            {
                //hardcode path
            }
            j=0;
        }
        return new int[1][2];
    }

    public int[][] getCommands(int start, int end){
        int[][] temp = getCmd(start, end);
        int[][] retval = new int [temp.length][2];
        if(temp[0][0]==0)
        {
            for(int i = 0;i<=temp.length-1;i++)
            {
                retval[i]=temp[temp.length-1-i];
            }
        }
        else
        {
            for(int i = 0;i<=temp.length-1;i++)
            {
                retval[temp.length-1-i]=temp[i];
            }
        }
        return retval;
    }
}
