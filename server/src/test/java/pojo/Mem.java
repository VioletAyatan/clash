package pojo;

import lombok.Data;
import org.example.api.pojo.ClanCapital;
import org.example.api.pojo.ClanMember;

@Data
public class Mem {
    private String tag;
    private String name;

    public Mem(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public static Mem create(ClanCapital.ClanCapitalMember member) {
        return new Mem(member.getTag(), member.getName());
    }

    public static Mem create(ClanMember clanMember) {
        return new Mem(clanMember.getTag(), clanMember.getName());
    }
}
