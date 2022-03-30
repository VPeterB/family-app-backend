package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.UserDTO
import hu.bme.aut.familyappbackend.model.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun convertToDto(user: User) : UserDTO{
        val shoppingIDS: MutableList<Int> = mutableListOf()
        for (shoppingList in user.shoppingLists!!){
            shoppingIDS.add(shoppingList.ID)
        }
        return UserDTO(user.ID, user.email, user.password, user.phonenumber, user.firstname, user.lastname, user.username, user.category,
            user.family?.ID, user.invite?.ID, shoppingIDS)
    }

    fun convertToModel(user: UserDTO) : User{
        //TODO family, invite, shoppingLists 'bevarázsolása'
        return User(user.ID, user.email, user.password, user.phonenumber, user.firstname, user.lastname, user.username, user.category)
    }
}