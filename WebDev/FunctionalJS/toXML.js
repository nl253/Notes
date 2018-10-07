#!/usr/bin/env node

const fs = require('fs');
const process = require('process');

function toXML(dict = {}, rootTagName = 'xml') {
  return `<${rootTagName}>${_toXML(dict)}</${rootTagName}>`;
}

function _toXML(dict = {}) {
  if (Object.getPrototypeOf(dict) // str
    .constructor.name === Object.getPrototypeOf(' ')
    .constructor.name) {
    return dict;
  } else if (Object.getPrototypeOf(dict) // dict 
    .constructor.name === Object.getPrototypeOf({})
    .constructor.name) {
    return Object.entries(dict)
      .map(entry => `<${entry[0]}>${_toXML(entry[1])}</${entry[0]}>`)
      .join('');
  } else if (Object.getPrototypeOf(dict) // array
    .constructor.name === Object.getPrototypeOf([])
    .constructor.name) {
    return Object.values(dict)
      .map(value => `<item>${_toXML(value)}</item>`)
      .join('');
  } else return dict; // bool, num
}

for (const file of process.argv.slice(2)) {
  fs.readFile(file, (err, res) => {
    if (!err) console.log(toXML(JSON.parse(res), file));
    else console.error(err);
  });
}
