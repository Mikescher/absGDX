#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "Mikescher/absGDX" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
    cp -R absGDX-framework/build/docs/javadoc $HOME/tmp_jd
    
    cd $HOME
    
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "travis-ci"
    git clone --branch=gh-pages https://${GH_TOKEN}@github.com/Mikescher/absGDX gh-pages
    cd gh-pages

    git rm -rf ./javadoc

    cp -Rf $HOME/tmp_jd ./javadoc

    git add -f .
    git status
    git commit -m "Lastest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
    git push -f origin gh-pages
fi
