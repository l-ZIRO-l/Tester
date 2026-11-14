import js from '@eslint/js';
import tseslint from 'typescript-eslint';

export default tseslint.config(
  { ignores: ['dist/', 'node_modules/'] },
  js.configs.recommended,
  ...tseslint.configs.recommended,
  {
    languageOptions: {
      globals: { window: 'readonly', document: 'readonly', console: 'readonly' },
    },
    rules: {
      // Frontend Safety guardrail (vault note "Frontend Safety"): ban raw HTML injection APIs.
      'no-restricted-syntax': [
        'error',
        { selector: "MemberExpression[property.name='innerHTML']", message: 'innerHTML is banned; render text via React only.' },
        { selector: "MemberExpression[property.name='outerHTML']", message: 'outerHTML is banned; render text via React only.' },
        { selector: "MemberExpression[property.name='dangerouslySetInnerHTML']", message: 'dangerouslySetInnerHTML is banned.' },
        { selector: "CallExpression[callee.object.name='document'][callee.property.name='write']", message: 'document.write is banned.' },
      ],
    },
  }
);
