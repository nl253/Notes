/** @format */

class Manager {
  constructor(selector, tag) {
    this._selector = selector;
    this._renderTime = 1000;
    this._tag = tag;
    this._uid = 'mount-node';
  }

  get renderTime() {
    return this._renderTIme;
  }

  set renderTime(time) {
    this._renderTIme = time;
  }

  get selector() {
    return this._selector;
  }

  set selector(selector) {
    this._selector = selector;
  }

  get tag() {
    return this._tag;
  }

  set tag(tag) {
    this._tag = tag;
  }

  mount() {
    if (this.tag.changed) {
      console.info('content changed');
      const node = document.querySelector(this.selector);
      if (!node) {
        console.log('could not select node using ' + this.selector + ' found ' + node);
      } else {
        console.log('found node ' + node);
      }
      const child = document.createElement('div');
      child.outerHTML = this.tag.render();
      console.log(child);
      console.log('rerendered app ' + child.outerHTML);
      const tryFind = node.querySelector(this._uid);
      if (tryFind) {
        console.log('removing old ...');
        node.removeChild(tryFind);
      }
      console.log('rerendering');
      node.appendChild(child);
    }
    console.info('scheduling next check');
    setTimeout(this.mount, this.renderTime);
  }
}
