package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.CreateShoppingItemDTO
import hu.bme.aut.familyappbackend.dto.GetShoppingItemDTO
import hu.bme.aut.familyappbackend.mapper.ShoppingItemMapper
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.repository.ShoppingItemRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ShoppingItemService (private val shoppingItemRepository: ShoppingItemRepository, private val shoppingListRepository: ShoppingListRepository){
    fun save (shoppingItem: CreateShoppingItemDTO, shoppingList: ShoppingList): ShoppingItem{
        val newSI = ShoppingItem(0, shoppingItem.name, shoppingItem.done, Timestamp(System.currentTimeMillis()), shoppingList)
        var shoppingItems: MutableList<ShoppingItem> = mutableListOf()
        if(shoppingList.shoppingItems != null){
            shoppingItems = shoppingList.shoppingItems as MutableList<ShoppingItem>
        }
        shoppingItems.add(newSI)
        shoppingList.shoppingItems = shoppingItems
        shoppingList.lastModTime = Timestamp(System.currentTimeMillis())
        shoppingListRepository.save(shoppingList)
        return shoppingItemRepository.save(newSI)
    }

    fun delete (shoppingItem: ShoppingItem){
        val shoppingList = shoppingItem.shoppingList
        if(shoppingList?.shoppingItems != null) {
            val lShoppingItems: MutableList<ShoppingItem> = shoppingList.shoppingItems as MutableList<ShoppingItem>
            if (lShoppingItems.contains(shoppingItem)) {
                lShoppingItems.remove(shoppingItem)
                shoppingList.shoppingItems = lShoppingItems
                shoppingList.lastModTime = Timestamp(System.currentTimeMillis())
                shoppingListRepository.save(shoppingList)
            }
        }
        shoppingItemRepository.delete(shoppingItem)
    }

    fun edit(shoppingItem: ShoppingItem, si: ShoppingItem): GetShoppingItemDTO {
        shoppingItem.shoppingList = si.shoppingList
        shoppingItem.lastModTime = Timestamp(System.currentTimeMillis())
        val sI= shoppingItemRepository.save(shoppingItem)
        val shoppingItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        return shoppingItemMapper.convertToDto(sI)
    }

    fun byShoppingList(sList: ShoppingList): MutableList<GetShoppingItemDTO> {
        val rSIs = mutableListOf<GetShoppingItemDTO>()
        val shoppingItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        if(sList.shoppingItems != null){
            val shoppingItems = sList.shoppingItems as MutableList<ShoppingItem>
            for(si in shoppingItems){
                val shoppingItemDTO = shoppingItemMapper.convertToDto(si)
                rSIs.add(shoppingItemDTO)
            }
        }
        return rSIs
    }

    fun undone(si: ShoppingItem): GetShoppingItemDTO{
        si.done = false
        si.lastModTime = Timestamp(System.currentTimeMillis())
        val sI= shoppingItemRepository.save(si)
        val shoppingItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        return shoppingItemMapper.convertToDto(sI)
    }

    fun done(shoppingItem: ShoppingItem): GetShoppingItemDTO {
        shoppingItem.done = true
        shoppingItem.lastModTime = Timestamp(System.currentTimeMillis())
        val sI= shoppingItemRepository.save(shoppingItem)
        val shoppingItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        return shoppingItemMapper.convertToDto(sI)
    }
}