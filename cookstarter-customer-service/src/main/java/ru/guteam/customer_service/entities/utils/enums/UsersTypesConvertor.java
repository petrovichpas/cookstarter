package ru.guteam.customer_service.entities.utils.enums;

import ru.guteam.customer_service.entities.utils.enums.UsersTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UsersTypesConvertor implements AttributeConverter<UsersTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(UsersTypeEnum usersType) {
        if (usersType == null) {
            return null;
        }
        return usersType.getCode();
    }

    @Override
    public UsersTypeEnum convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(UsersTypeEnum.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);    }
}
