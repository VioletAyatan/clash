package pojo;

import lombok.Data;
import org.ankol.server.api.entity.ClanMember;
import org.ankol.server.api.entity.RaidSeason;


@Data
public class Mem {
    private String tag;
    private String name;

    public Mem(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public static Mem create(RaidSeason.RaidSeasonMember member) {
        return new Mem(member.getTag(), member.getName());
    }

    public static Mem create(ClanMember clanMember) {
        return new Mem(clanMember.getTag(), clanMember.getName());
    }
}
