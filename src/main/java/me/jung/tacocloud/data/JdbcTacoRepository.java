package me.jung.tacocloud.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jung.tacocloud.Ingredient;
import me.jung.tacocloud.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository{

    private final JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient,tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "Insert into Taco(name, createdAt) values(?,?)", Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(Boolean.TRUE);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), Timestamp.valueOf(taco.getCreatedAt())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) " +
                        "values (?, ?)",
                tacoId,ingredient.getId()
        );

    }

}
