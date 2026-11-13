---
tags: [runflow/guardrail]
status: draft
---
# Frontend Safety

User-controlled strings reach the UI everywhere: stdout, string values, class/field/method names, CFG labels (derived from source), exception messages, compile diagnostics.

## Rules
1. **No `dangerouslySetInnerHTML`, no `innerHTML`, no D3 `.html()`.** Text only (React text nodes / SVG `<text>`). ESLint rule bans these.
2. Strip/visualize control characters and ANSI escapes; neutralize bidi override chars (U+202A–202E, U+2066–2069) in displayed text.
3. Truncate every string by length before display; never render unbounded text.
4. **Validate the trace with zod** before use (types, enums, size caps). Unknown `kind`s → raw fallback, never crash.
5. **CSP** (starting point): `default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; connect-src 'self' <api-origin>; worker-src 'self' blob:; frame-ancestors 'none'; object-src 'none'; base-uri 'none'` (tune for Monaco workers).
6. Other headers: `X-Content-Type-Options: nosniff`, `Referrer-Policy: no-referrer`, HSTS.
7. Error boundaries per panel; one bad renderer must not blank the app.
8. Don't use user text as React `key`s, CSS selectors, or element ids without escaping.
9. No third-party scripts/analytics in MVP.
10. When sharing is added later (post-MVP) → new XSS review ([[Post-MVP Parking Lot]]).

## Tests
Programs A19–A22 in [[Adversarial Test Corpus]] (`<img onerror>`, `</script>`, ANSI, RTL override) must render inert.
