import com.snakelin.game.*
import com.snakelin.game.GameEngine.step
import kotlin.test.*

class GameStateTest {
    @Test
    fun whenGameStateStepFromStart_getGoodOutput() {
        val game = GameState(
                5,
                Snake(Point(1,2), mutableListOf(Point(2,2), Point(2,3)), Direction.WEST),
                Apple(Point(1, 1))
        )
        game.step()
        assertEquals(Point(0,2), game.player.head)
        assertEquals(Point(1, 2), game.player.body[0])
        assertEquals(Point(2, 2), game.player.body[1])
    }
}