/** @format */

class Builder {
  constructor(tagName) {
    this._tagName = tagName;
    this._children = [];
    this._styles = {};
    this._wrap = [];
    this._flagAttrs = [];
    this._attrs = {};
    this._text = '';
    this._classes = [];
    this._eventListeners = {};
  }

  classes(classes) {
    for (let c of classes) {
      this._classes.push(c);
    }
    return this;
  }

  class(c) {
    this._classes.push(c);
    return this;
  }

  id(id) {
    this._attrs.id = id;
    return this;
  }

  on(event, funct) {
    if (!this._eventListeners[event]) {
      this._eventListeners[event] = [];
    }
    this._eventListeners[event].push(funct);
    return this;
  }

  style(k, v) {
    this._styles[k] = v;
    return this;
  }

  styles(dict) {
    for (const key in dict) {
      this._styles[key] = dict[key];
    }
    return this;
  }

  tagName(name) {
    this._tagName = name;
    return this;
  }

  attr(k, v) {
    if (v) this._attrs[k] = v;
    else this._flagAttrs.push(k);
    return this;
  }

  attrs(attrs) {
    if (attrs.__proto__.constructor.name === 'Array') {
      this._flagAttrs = this._flagAttrs.concat(attrs);
    } else {
      for (const key in attrs) {
        this._attrs[key] = attrs[key];
      }
    }
    return this;
  }

  text(text) {
    this._text = text;
    return this;
  }

  append(text) {
    this._text += text;
    return this;
  }

  children(children) {
    this._children = this._children.concat(children);
    return this;
  }

  wrap(beforeTag) {
    this._wrap.push(beforeTag);
    return this;
  }

  child(child) {
    this._children.push(child);
    return this;
  }

  build() {
    let node = document.createElement(this._tagName);

    node.innerText = this._text;

    for (const key in this._attrs) {
      node.setAttribute(key, this._attrs[key]);
    }

    for (const attr of this._flagAttrs) {
      node.setAttribute(attr, '');
    }

    for (const child of this._children) {
      node.appendChild(child);
    }

    for (const c of this._classes) {
      node.classList.add(c);
    }

    for (const key in this._styles) {
      node.style[key] = this._styles[key];
    }

    for (const event in this._eventListeners) {
      for (const callback of this._eventListeners[event]) {
        node.addEventListener(event, callback);
      }
    }

    for (const wrapper of this._wrap) {
      const wrapperNode = document.createElement(wrapper);
      wrapperNode.appendChild(node);
      node = wrapperNode;
    }

    return node;
  }
}

export default Builder;

