public class Characters // Character getter methods here
{
    private String name;
    private String birthday;
    private String region;
    private String description;
    private int rarity;
    private String weapon;
    private String element;
    private String constellation;

    public Characters(String name, String birthday, String region, String description, int rarity, String weapon, String element, String constellation)
    {
        this.name = name;
        this.birthday = birthday;
        this.region = region;
        this.description = description;
        this.rarity = rarity;
        this.weapon = weapon;
        this.element = element;
        this.constellation = constellation;
    }

    public String getName()
    {
        return name;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getRegion()
    {
        return region;
    }

    public String getDescription()
    {
        return description;
    }

    public int getRarity()
    {
        return rarity;
    }

    public String getWeapon()
    {
        return weapon;
    }

    public String getElement()
    {
        return element;
    }

    public String getConstellation()
    {
        return constellation;
    }
}