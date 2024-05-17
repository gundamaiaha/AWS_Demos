INSERT INTO users (username, password, email) VALUES ('john_doe', 'password123', 'john@example.com');

INSERT INTO quizzes (title, description) VALUES ('Sample Quiz', 'This is a sample quiz description.');

INSERT INTO questions (quiz_id, question_text) VALUES (1, 'What is the capital of France?');

INSERT INTO choices (question_id, choice_text, is_correct) VALUES (1, 'Paris', TRUE);
INSERT INTO choices (question_id, choice_text, is_correct) VALUES (1, 'London', FALSE);
INSERT INTO choices (question_id, choice_text, is_correct) VALUES (1, 'Berlin', FALSE);
INSERT INTO choices (question_id, choice_text, is_correct) VALUES (1, 'Madrid', FALSE);


INSERT INTO user_answers (user_id, question_id, choice_id) VALUES (1, 1, 1);

INSERT INTO scores (user_id, quiz_id, score) VALUES (1, 1, 1);

INSERT INTO questions (quiz_id, question_text) VALUES
    (1, 'What is the capital of France?'),
    (1, 'What is 2 + 2?'),
    (1, 'Who wrote "To Kill a Mockingbird"?');

INSERT INTO choices (question_id, choice_text, is_correct) VALUES
    -- Choices for question 1
    (1, 'Paris', TRUE),
    (1, 'London', FALSE),
    (1, 'Berlin', FALSE),
    (1, 'Madrid', FALSE),
    -- Choices for question 2
    (2, '3', FALSE),
    (2, '4', TRUE),
    (2, '5', FALSE),
    (2, '6', FALSE),
    -- Choices for question 3
    (3, 'Harper Lee', TRUE),
    (3, 'Mark Twain', FALSE),
    (3, 'Ernest Hemingway', FALSE),
    (3, 'F. Scott Fitzgerald', FALSE);


INSERT INTO user_answers (user_id, question_id, choice_id) VALUES
    (1, 1, 1), -- User chose 'Paris' for question 1
    (1, 2, 6), -- User chose '4' for question 2
    (1, 3, 9); -- User chose 'Harper Lee' for question 3


WITH correct_answers AS (
    SELECT ua.user_id, q.quiz_id, COUNT(*) AS score
    FROM user_answers ua
    JOIN choices c ON ua.choice_id = c.choice_id
    JOIN questions q ON ua.question_id = q.question_id
    WHERE c.is_correct = TRUE AND ua.user_id = 1 AND q.quiz_id = 1
    GROUP BY ua.user_id, q.quiz_id
)
INSERT INTO scores (user_id, quiz_id, score)
SELECT user_id, quiz_id, score FROM correct_answers;
