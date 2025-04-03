package young.refactoring.ch11.replaceConstructorWithFactoryFunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {

    private Exam exam;
    private Document document;

    @BeforeEach
    void setUp() {
        document = new Document();
        document.name = "Alice";
        document.empType = "Manager";

        document.leadEngineerName = "Bob";
//        document.empType = "Engineer";

        exam = new Exam();
        // document 필드 주입
        try {
            var field = Exam.class.getDeclaredField("document");
            field.setAccessible(true);
            field.set(exam, document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("기본 직원 정보를 사용해 Employee 생성")
    void testClient_createsEmployeeFromDocument() {
        Employee employee = new Employee(document.name, document.empType);
        assertEquals("Alice", employee.getName());
        assertEquals("Manager", employee.getType());
    }

    @Test
    @DisplayName("리드 엔지니어 정보를 사용해 Employee 생성")
    void testClient2_createsLeadEngineerFromDocument() {
        Employee lead = new Employee(document.leadEngineerName, "Engineer");
        assertEquals("Bob", lead.getName());
        assertEquals("Engineer", lead.getType());
    }

}