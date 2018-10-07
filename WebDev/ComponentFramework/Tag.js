/** @format */

class Tag {

  constructor(tag) {
    this._tag = tag;
    this._style = {};
    this._changed = true;
    this._attrs = {};
    this._text = '';
    this._children = [];
  }

  get style() {
    return this._style;
  }

  set style(style) {
    this._style = style;
  }

  get attrs() {
    return this._attrs;
  }

  get children() {
    return this._children;
  }

  set children(xs) {
    this._children = xs;
  }

  set attrs(attrs) {
    this._attrs = attrs;
  }

  get text() {
    return this._text;
  }

  set text(text) {
    this._text = text;
  }

  get tag() {
    return this._tag;
  }

  get changed() {
    return this._changed || this.children.some(x => x.changed());
  }

  setChanged() {
    this._changed = true;
  }

  render() {
    let attributes = '';

    if (Object.entries(this.attrs).length > 0) {
      attributes = ` ${Object.entries(this.attrs).map(entry => `${entry[0]}="${entry[1]}"`)}`;
    }

    let middle = this.text;

    if (this.children.length > 0) {
      middle = this.children.map(child => child.render()).join('');
    }
    this._changed = false;
    return `<${this.tag}${attributes}>${middle}</${this.tag}>`;
  }
}
