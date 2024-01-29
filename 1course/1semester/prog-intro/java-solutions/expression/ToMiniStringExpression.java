package expression;

public interface ToMiniStringExpression extends Expression, TripleExpression, ToMiniString {
    // Note:: not needed for user
    void toString(StringBuilder sb);
}
