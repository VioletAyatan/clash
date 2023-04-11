package pojo;

import com.ankol.api.entity.ClanMember;
import com.ankol.api.entity.RaidSeason;
import lombok.Data;


@Data
public class Mem {
    private String tag;
    private String name;

    public Mem(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public static Mem create(RaidSeason.MembersDTO member) {
        return new Mem(member.getTag(), member.getName());
    }

    public static Mem create(ClanMember clanMember) {
        return new Mem(clanMember.getTag(), clanMember.getName());
    }
}
