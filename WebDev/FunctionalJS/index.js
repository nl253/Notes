const type = x => x.constructor.toLocaleString()
  .split(' ')[1].toLowerCase()
  .replace(/\(.*\)/, '');

function all(xs, f = Boolean) {
  const head = xs.slice(0, 1);
  return head.length === 0 ? true : f(head[0]) && all(xs.slice(1));
}

function any(xs, f = Boolean) {
  const head = xs.slice(0, 1);
  return head.length === 0 || f(head[0]) ? true : any(xs.slice(1));
}

function nested(n) {
  if (n === 0) {
    return {};
  }
  const x = {};
  for (let i = 0; i < n; i++) {
    x[i] = nested(n - 1);
  }
  return x;
}

function deepcopy(x) {
  const copy = {};
  Object.keys(x)
    .forEach((key) => {
      const val = x[key];
      if (typeof val === 'object') {
        copy[key] = deepcopy(val);
      } else {
        copy[key] = val;
      }
    });
  return copy;
}
const flatten = x => (type(x) === 'array' ? flattenArray(x) : flattenObj(x));

function flattenArray(x) {
  const copy = [];
  Object.keys(x)
    .forEach((key) => {
      const val = x[key];
      if (typeof x === 'object') {
        flatten(val)
          .forEach(y => copy.push(y));
      } else {
        copy.push(val);
      }
    });
  return copy;
}

/*
 * const flattenObj = x => {
 * const copy = {}
 * Object.keys(x)
 * .forEach(key => {
 * const val = x[key]
 * if (typeof x === 'object') {
 * let inner = flatten(val);
 * Object.keys(inner)
 * .forEach(y => copy[inner[y].push(y))
 * }
 * else {
 * copy[key] = val
 * }
 * }) return copy
 * }
 */
