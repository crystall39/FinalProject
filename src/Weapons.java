public class Weapons // Weapon getter methods here
{
    private String name;
    private String type;
    private int rarity;
    private int baseAtk;
    private String subStatType;
    private double subStat;
    private String description;
    private String abilityName;
    private String abilityDescription;
    private String location;

    public Weapons(String name, String type, int rarity, int baseAtk, String subStatType, double subStat, String description, String abilityName, String abilityDescription, String location)
    {
        this.name = name;
        this.type = type;
        this. rarity = rarity;
        this.baseAtk = baseAtk;
        this.subStatType = subStatType;
        this.subStat = subStat;
        this.description = description;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getRarity()
    {
        return rarity;
    }

    public int getBaseAtk()
    {
        return baseAtk;
    }

    public String getSubStatType()
    {
        return subStatType;
    }

    public double getSubStat()
    {
        return subStat;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAbilityName()
    {
        return abilityName;
    }

    public String getAbilityDescription()
    {
        return abilityDescription;
    }

    public String getLocation()
    {
        return location;
    }
}