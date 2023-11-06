-- liquibase formatted sql

-- changeset aiunusov:1
CREATE INDEX student_name ON student(name);

-- changeset aiunusov:2
CREATE INDEX faculty_name_color on faculty (name, color);
