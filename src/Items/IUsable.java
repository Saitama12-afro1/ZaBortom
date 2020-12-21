package Items;

import Main.Game_obj.GamePerson;

// Предметы, которые можно использовать, имплементируют этот интерфейс.
public interface IUsable {
    // Активирует эффект карты, не удаляет её из руки владельца
    // Возвращает исключение, если эта карта не может быть использована
    void use(GamePerson person) throws Exception;

    // Метод canUse возвращает false, если использовать предмет нельзя, true в противном случае.
    boolean canUse(GamePerson person);

}
