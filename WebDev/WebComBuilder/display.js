/** @format */

const root = '#root';

const display = node => {
  const appRoot = document.querySelector(root);
  appRoot.childNodes.forEach(child => child.remove());
  appRoot.appendChild(node);
};

export default display;
