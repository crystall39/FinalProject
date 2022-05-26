public class Artifacts // Artifact getter methods here
{
    private String name;
    private int maxRarity;
    private String twoPieceBonus;
    private String fourPieceBonus;

    public Artifacts(String name, int maxRarity, String twoPieceBonus, String fourPieceBonus)
    {
        this.name = name;
        this.maxRarity = maxRarity;
        this.twoPieceBonus = twoPieceBonus;
        this.fourPieceBonus = fourPieceBonus;
    }

    public String getName()
    {
        return name;
    }

    public int getMaxRarity()
    {
        return maxRarity;
    }

    public String getTwoPiece()
    {
        return twoPieceBonus;
    }

    public String getFourPiece()
    {
        return fourPieceBonus;
    }
}