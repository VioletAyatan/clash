package org.ankol.server.services.impl

import org.ankol.server.dao.ClanMemberRepository
import org.ankol.server.dao.RaidSeasonRepository
import org.ankol.server.dao.entity.ClanMemberEntity
import org.ankol.server.dao.entity.RaidSeasonEntity
import org.ankol.server.services.ClashDataOperationService
import org.ankol.server.services.ClashServices
import org.ankol.server.tools.BusinessException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClashServicesImpl : ClashServices {
    private val log: Logger = LoggerFactory.getLogger(ClashServicesImpl::class.java)

    @Autowired
    private lateinit var raidSeasonRepository: RaidSeasonRepository

    @Autowired
    private lateinit var clashDataOperationService: ClashDataOperationService

    @Autowired
    private lateinit var clanMemberRepository: ClanMemberRepository

    override fun saveRaidSeason(raidSeason: RaidSeasonEntity) {
        raidSeasonRepository.save(raidSeason)
    }

    override fun listRaidSeason(): List<RaidSeasonEntity> {
        return raidSeasonRepository.findAll()
    }

    override fun findRaidSeasonById(id: String): RaidSeasonEntity {
        return raidSeasonRepository.findById(id)
            .orElseThrow { BusinessException("未查找找到对应的突袭周末战绩数据") }
    }

    override fun getClanMember(): List<ClanMemberEntity> {
        val allMembers = clanMemberRepository.findAll()
        if (allMembers.isEmpty()) {
            return clashDataOperationService.triggerClanMemberUpdate()
        }
        return allMembers
    }
}
