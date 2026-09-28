# How to release a new version

1. Update `Readme.md` with new version.
2. Add changelog in `History.md`.
3. Merge to `master`, then tag it: `git tag X.X.X && git push origin X.X.X`.
4. In the consuming project, check out the new tag in the submodule and commit the updated pin.
