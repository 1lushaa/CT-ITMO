package expression;

public interface ToMiniStringExpression extends Expression, TripleExpression, ToMiniString {
    void toString(StringBuilder sb);
}
