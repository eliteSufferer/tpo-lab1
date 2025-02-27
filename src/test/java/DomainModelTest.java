import org.example.task3.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Доменная модель сцены испарения")
class DomainModelTest {

    private Man man;
    private Woman woman;
    private EvaporationEvent event;

    @BeforeEach
    void setUp() {
        man = new Man("Смеющийся человек");
        woman = new Woman("Девушка");
        event = new EvaporationEvent();
    }

    @Nested
    @DisplayName("Тесты базового функционала персонажей")
    class CharacterTests {

        @Test
        @DisplayName("Создание персонажа с правильным именем")
        void personCreation() {
            assertEquals("Смеющийся человек", man.getName());
            assertEquals("Девушка", woman.getName());
        }

        @Test
        @DisplayName("Начальное состояние персонажей")
        void initialState() {
            assertAll(
                    () -> assertEquals(EvaporationState.ALIVE, man.getState()),
                    () -> assertEquals(EvaporationState.ALIVE, woman.getState())
            );
        }
    }

    @Nested
    @DisplayName("Тесты специфичного поведения")
    class BehaviorTests {

        @Test
        @DisplayName("Мужчина может смеяться")
        void manLaugh() {
            assertDoesNotThrow(() -> man.laugh());
        }

        @Test
        @DisplayName("Девушка может испытывать ненависть")
        void womanHate() {
            woman.hate(man);
            assertTrue(woman.isHatesMan());
        }
    }

    @Nested
    @DisplayName("Тесты событий испарения")
    class EvaporationTests {

        @Test
        @DisplayName("Испарение одного персонажа")
        void singleEvaporation() {
            event.triggerEvaporation(man);
            assertEquals(EvaporationState.EVAPORATED, man.getState());
        }

        @Test
        @DisplayName("Логирование временного промежутка")
        void timeLogging() {
            assertDoesNotThrow(() -> event.logTimePassing(90));
        }
    }

    @Nested
    @DisplayName("Интеграционные тесты сценария")
    class IntegrationTests {

        @Test
        @DisplayName("Полный сценарий испарения")
        void fullEvaporationScenario() {
            // Act
            man.laugh();
            woman.hate(man);
            event.logTimePassing(90);
            event.triggerEvaporation(man);
            event.triggerEvaporation(woman);

            // Assert
            assertAll(
                    () -> assertEquals(EvaporationState.EVAPORATED, man.getState()),
                    () -> assertEquals(EvaporationState.EVAPORATED, woman.getState()),
                    () -> assertTrue(woman.isHatesMan())
            );
        }

        @Test
        @DisplayName("Попытка повторного испарения")
        void doubleEvaporationAttempt() {
            event.triggerEvaporation(man);
            event.triggerEvaporation(man); // Вторая попытка

            assertAll(
                    () -> assertEquals(EvaporationState.EVAPORATED, man.getState()),
                    () -> assertDoesNotThrow(() -> event.triggerEvaporation(man))
            );
        }
    }

    @Test
    @DisplayName("Проверка состава испарения")
    @Tag("Chemistry")
    void evaporationCompositionTest() {
        event.triggerEvaporation(man);
        assertEquals(EvaporationState.EVAPORATED, man.getState());
        // Здесь можно добавить проверки состава, если добавим соответствующую логику
    }
}