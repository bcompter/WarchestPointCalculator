package main;

/**
 *
 * @author Brian Compter
 */
public class Calculator {

    // A static instance of the calculator object
    private static final Calculator INSTANCE = new Calculator();

    public static final int WARCHEST = 0;
    public static final int SUPPORT = 1;
    public static final int CBILLS = 2;

    private int warchest, support, cbills;
    private int fsm;
    private double tr;

    private int lastUpdate = 0;

    public int[] recalc()
    {
        if ( lastUpdate == WARCHEST )
            return calculatebyWarchest(warchest);
        else if ( lastUpdate == SUPPORT )
            return calculatebySupport(support);
        else
            return calculatebyCBills(cbills);
    }

    public int[] calculatebyWarchest(int in)
    {
        warchest = in;
        int[] retval = new int[3];

        retval[WARCHEST] = warchest;
        retval[SUPPORT] = (int) (warchest * fsm * tr);
        retval[CBILLS] = retval[SUPPORT] * 10000;

        lastUpdate = WARCHEST;

        return retval;
    }

    public int[] calculatebySupport(int in)
    {
        support = in;
        int[] retval = new int[3];

        retval[SUPPORT] = support;
        retval[WARCHEST] = (int) Math.ceil((double)support / (double)fsm / tr);
        retval[CBILLS] = retval[SUPPORT] * 10000;

        lastUpdate = SUPPORT;

        return retval;
    }

    public int[] calculatebyCBills(int in)
    {
        cbills = in;
        int[] retval = new int[3];

        retval[CBILLS] = cbills;
        retval[SUPPORT] = retval[CBILLS] / 10000;
        retval[WARCHEST] = (int) Math.ceil((double)retval[SUPPORT] / (double)fsm / tr);

        lastUpdate = CBILLS;

        return retval;
    }

    public void setParameters(int i, double d)
    {
        fsm = i;
        tr = d;
    }

    // Private constructor prevents instantiation from other classes
    private Calculator() {
        warchest = support = cbills = 0;
    }

    // Return the calculator instance
    public static Calculator getInstance() {
        return INSTANCE;
    }

}
