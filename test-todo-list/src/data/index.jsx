export class User {
  constructor(name) {
    this.name = name;
    this.todoList = [];
  }
}

export class Todo {
  constructor(text, id) {
    this.text = text;
    this.id = id;
    this.success = false;
  }
}

