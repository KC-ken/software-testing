import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private int initSpeed = 100;
    private String initDir = "West";
    private Boolean finalized;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle(initSpeed, initDir);
        finalized = false;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if (!finalized) {
            vehicle1.finalize();
            vehicle2.finalize();
        }
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        vehicle1.finalize();
        vehicle2.finalize();
        finalized = true;
        assertEquals(0, vehicle1.totalVehicle());
        assertEquals(0, vehicle2.totalVehicle());

    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        int speed = 123;
        vehicle1.setSpeed(speed);
        assertEquals(speed, vehicle1.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        String Dir = "south";
        vehicle1.setDir(Dir);
        assertEquals(Dir, vehicle1.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, vehicle1.getSpeed());
        assertEquals(initSpeed, vehicle2.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north", vehicle1.getDir());
        assertEquals(initDir, vehicle2.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(2, vehicle1.totalVehicle());
        assertEquals(2, vehicle2.totalVehicle());
    }
}