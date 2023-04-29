package ru.otus.csv.mapper;

import org.junit.jupiter.api.Test;
import ru.otus.csv.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringToQuestionMapperTest {
  @Test
  void testMapStringToQuestion() {
    QuestionMapper<String> mapper = new StringToQuestionMapper();
    String line = "In which Italian city can you find the Colosseum?, Venice, Rome, Naples, Milan, Rome";
    Question question = mapper.map(line);
    assertEquals("In which Italian city can you find the Colosseum?", question.getQuestion());
    assertEquals(List.of("Venice", "Rome", "Naples", "Milan"), question.getOptions());
    assertEquals("Rome", question.getCorrectAnswer());
  }
}
