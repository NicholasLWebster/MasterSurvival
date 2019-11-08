import org.junit.jupiter.api.Test;

public class RadiusScanAlgorithmTest {

    private static final int RADIUS = 10;
    private static final int DIAMETER = RADIUS * 2;

    @Test
    public void testPlayerRadiusAlgorithm() {
        boolean[][][] xyzTable = new boolean[DIAMETER + 6][DIAMETER + 6][DIAMETER + 6];

        int xOrigin = RADIUS;
        int yOrigin = RADIUS;
        int zOrigin = RADIUS;

        int yStart = 0 - RADIUS;
        int yEnd = 0 + RADIUS;
        for (int y = yStart; y <= yEnd; y++) {
            int xRange = RADIUS - Math.abs(y) - 1; // -1 to account for origin block
            int xStart = 0 - xRange;
            int xEnd = 0 + xRange;
            for (int x = xStart; x <= xEnd; x++) {
                int zRange = RADIUS - Math.abs(y) - Math.abs(x) - 1; // -1 to account for origin block
                int zStart = 0 - zRange;
                int zEnd = 0 + zRange;
                for (int z = zStart; z <= zEnd; z++) {
                    int xWorking = x + xOrigin;
                    int yWorking = y + yOrigin;
                    int zWorking = z + zOrigin;
                    xyzTable[xWorking][yWorking][zWorking] = true;
                }
            }
        }
        print_xy(xyzTable);
        print_yz(xyzTable);
        print_xz(xyzTable);
    }


    private void print_xy(boolean[][][] xyzTable){
        int z = RADIUS;
        System.out.println("Printing XY:");
        for (int y = 0; y < xyzTable.length; y++) {
            for (int x = 0; x < xyzTable[y].length; x++) {
                String printable = xyzTable[x][y][z] ? "X" : " ";
                System.out.print(printable);
            }
            System.out.print(System.lineSeparator());
        }
        System.out.print(System.lineSeparator());
    }

    private void print_yz(boolean[][][] xyzTable){
        int x = RADIUS;
        System.out.println("Printing YZ:");
        for (int y = 0; y < xyzTable.length; y++) {
            for (int z = 0; z < xyzTable[y][x].length; z++) {
                String printable = xyzTable[x][y][z] ? "X" : " ";
                System.out.print(printable);
            }
            System.out.print(System.lineSeparator());
        }
        System.out.print(System.lineSeparator());
    }

    private void print_xz(boolean[][][] xyzTable){
        int y = RADIUS;
        System.out.println("Printing XZ:");
        for (int x = 0; x < xyzTable[y].length; x++) {
            for (int z = 0; z < xyzTable[x][y].length; z++) {
                String printable = xyzTable[x][y][z] ? "X" : " ";
                System.out.print(printable);
            }
            System.out.print(System.lineSeparator());
        }
        System.out.print(System.lineSeparator());
    }
}
