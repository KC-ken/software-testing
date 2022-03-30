import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

final class paypalServiceFake implements paypalService {
//    @Override
    public String doDonate() {
        return "Success";
    }
}

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {
    private Player notoriousPlayer = new Player("9876", -100);
    private Player notoriousPlayer2 = new Player("9877", -100);
    private Player notoriousPlayer3 = new Player("9878", -100);
    private Player goodPlayer = new Player("6789", 100);

    private Hour hourStub;
    private Prison prisonSpy;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        hourStub = mock(Hour.class);
        Prison prison = new Prison();
        prisonSpy = spy(prison);

    }
//    @org.junit.jupiter.api.AfterEach
//    void tearDown() {
//    }

    // test a.
    @org.junit.jupiter.api.Test
    void test_a() throws InterruptedException {
        StrangeGame strangeGame1 = new StrangeGame();

        // set hour to stub hour, and set prison to spy prison
        strangeGame1.hour = hourStub;
        strangeGame1.prison = prisonSpy;

        // set the time to 6 a.m.
        when(hourStub.getHour()).thenReturn(6);

        // enter the game
        System.out.println(strangeGame1.enterGame(notoriousPlayer));

        //check if anyone has been arrested
        verify(strangeGame1.prison, never()).crime(notoriousPlayer);
    }

    // test b.
    @org.junit.jupiter.api.Test
    void test_b() throws InterruptedException {
        StrangeGame strangeGame1 = new StrangeGame();

        // set hour to stub hour, and set prison to spy prison
        strangeGame1.hour = hourStub;
        strangeGame1.prison = prisonSpy;

        // test enter the game in non-playable time
        // set the time to 6 a.m.
        when(hourStub.getHour()).thenReturn(6);
        // enter the game
        assertEquals("The game is not yet open!", strangeGame1.enterGame(notoriousPlayer));

        // test enter the game in playable time with reputation < 0
        // set the time to 1 p.m.
        when(hourStub.getHour()).thenReturn(13);
        // ignore the imprisonment
        doNothing().when(prisonSpy).imprisonment(any());
        // enter the game
        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame1.enterGame(notoriousPlayer));


        // test enter the game in playable time with reputation >= 0
        // set the time to 1 p.m.
        when(hourStub.getHour()).thenReturn(13);
        // enter the game
        assertEquals("Have a nice day!", strangeGame1.enterGame(goodPlayer));

    }

    // test c.
    @org.junit.jupiter.api.Test
    void test_c() throws InterruptedException {
        StrangeGame strangeGame1 = new StrangeGame();

        // set hour to stub hour, and set prison to spy prison
        strangeGame1.hour = hourStub;
        strangeGame1.prison = prisonSpy;

        // set the time to 1 p.m.
        when(hourStub.getHour()).thenReturn(13);

        // ignore the imprisonment
        doNothing().when(prisonSpy).imprisonment(any());

        strangeGame1.enterGame(notoriousPlayer);
        strangeGame1.enterGame(notoriousPlayer2);
        strangeGame1.enterGame(notoriousPlayer3);

        ArrayList playerIdList = new ArrayList<>();
        playerIdList.add("9876");
        playerIdList.add("9877");
        playerIdList.add("9878");
        assertEquals(playerIdList, prisonSpy.getLog());
    }

    // test d.
    @org.junit.jupiter.api.Test
    void test_d() throws InterruptedException {
        StrangeGame strangeGame1 = new StrangeGame();
        GAMEDb dbStub = mock(GAMEDb.class);

        // set hour to stub hour, prison to spy prison, and db to dbStub
        strangeGame1.hour = hourStub;
        strangeGame1.prison = prisonSpy;
        strangeGame1.db = dbStub;

        // set the time to 1 p.m.
        when(hourStub.getHour()).thenReturn(13);
        // reset the get score
        when(dbStub.getScore(any())).thenReturn(87);

        System.out.println(strangeGame1.enterGame(goodPlayer));
        System.out.println(strangeGame1.getScore("309511075"));
    }

    // test e.
    @org.junit.jupiter.api.Test
    void test_e() throws InterruptedException {
        StrangeGame strangeGame1 = new StrangeGame();
        paypalServiceFake paypalFake = new paypalServiceFake();

        // set hour to stub hour, and prison to spy prison
        strangeGame1.hour = hourStub;
        strangeGame1.prison = prisonSpy;

        // set the time to 1 p.m.
        when(hourStub.getHour()).thenReturn(13);

        System.out.println(strangeGame1.enterGame(goodPlayer));
        System.out.println(strangeGame1.donate(paypalFake));
    }
}

