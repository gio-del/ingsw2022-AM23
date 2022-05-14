package it.polimi.ingsw.utility.gamelimit;

import java.util.Map;

public class GameLimit {
    private static final Map<Integer, GameLimitData> gameLimitMap = new GameLimitParser().parseLimit();

    private GameLimit() {
    }

    /**
     * @param nPlayer the number of players
     * @return the GameLimitData associated with the given number of players
     */
    public static GameLimitData getLimit(int nPlayer) {
        return gameLimitMap.get(nPlayer);
    }

    public static void start() {
        //do nothing, if is not ok the parser will raise an exception and the server will stop
    }
}
