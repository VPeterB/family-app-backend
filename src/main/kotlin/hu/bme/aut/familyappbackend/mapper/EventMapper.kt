package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetEventDTO
import hu.bme.aut.familyappbackend.model.Event
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class EventMapper {
    abstract fun convertToDto(event: Event) : GetEventDTO

    @AfterMapping
    fun getId(@MappingTarget target: GetEventDTO, source: Event){
        val userIDS: MutableList<Int> = mutableListOf()
        for (user in source.users!!){
            userIDS.add(user.id)
        }
        target.userIDs = userIDS
        target.familyID = source.family?.id
    }
}