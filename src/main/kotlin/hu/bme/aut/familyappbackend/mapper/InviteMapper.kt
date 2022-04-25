package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetInviteDTO
import hu.bme.aut.familyappbackend.model.Invite
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class InviteMapper {
    abstract fun convertToDto(invite: Invite) : GetInviteDTO

    @AfterMapping
    fun getId(@MappingTarget target: GetInviteDTO, source: Invite) {
        target.familyID = source.family?.ID
        target.userID = source.user?.ID
    }
}