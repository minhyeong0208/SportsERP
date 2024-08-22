package acorn.shop.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class ShopDTO {

    // entity > dto 변환
    public static <E, D extends ShopDTO> D convertFromEntity(E entity, Class<D> dtoClass) {
        try {
            D dtoInstance = dtoClass.getDeclaredConstructor().newInstance();
            Map<String, Object> entityFields = getEntityFields(entity);

            for (Field dtoField : dtoClass.getDeclaredFields()) {
                String fieldName = dtoField.getName();
                if (entityFields.containsKey(fieldName)) {
                    dtoField.setAccessible(true);
                    dtoField.set(dtoInstance, entityFields.get(fieldName));
                }
            }
            return dtoInstance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert dto. cause :", e);
        }
    }

    // dto > entity 변환
    public <E> E convertToEntity(Class<E> entityClass) {
        try {
            E entityInstance = entityClass.getDeclaredConstructor().newInstance();
            Map<String, Object> dtoFields = getDTOFields(this);

            for (Field entityField : entityClass.getDeclaredFields()) {
                String fieldName = entityField.getName();
                if (dtoFields.containsKey(fieldName)) {
                    entityField.setAccessible(true);
                    entityField.set(entityInstance, dtoFields.get(fieldName));
                }
            }
            return entityInstance;
        } catch (Exception e) {
            throw new RuntimeException("DTO를 엔티티로 변환하는 데 실패했습니다.", e);
        }
    }

    // entity > Map<String, Object> 변환
    private static <E> Map<String, Object> getEntityFields(E entity) {
        Map<String, Object> fieldMap = new HashMap<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                fieldMap.put(field.getName(), field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldMap;
    }

    // dto > Map<String, Object> 변환
    private static <D extends ShopDTO> Map<String, Object> getDTOFields(D dto) {
        Map<String, Object> fieldMap = new HashMap<>();
        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                fieldMap.put(field.getName(), field.get(dto));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldMap;
    }
}