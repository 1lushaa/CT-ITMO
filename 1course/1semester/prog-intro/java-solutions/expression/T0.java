package expression;

public class T0 extends UnaryOperation {
    public T0(ToMiniStringExpression Exp) {
        super(Exp, "t0");
    }

    @Override
    protected int eval(int val) {
        if (val == 0) {
            return 32;
        } else {
            int cnt = 0;
            String binary = Integer.toBinaryString(val);
            for (int i = binary.length() - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') {
                    break;
                } else {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
