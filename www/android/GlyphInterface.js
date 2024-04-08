'use strict';

var exec = require('cordova/exec');

var GlyphInterface = {};

GlyphInterface.init = function(success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'init', []
    );
};

GlyphInterface.hasPermission = function(success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'hasPermission', []
    );
};

GlyphInterface.requestPermission = function(success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'request_permission', []
    );
};

GlyphInterface.createFrame = function(channels, period, cycles, interval, success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'createFrame', [channels, period, cycles, interval]
    );
};

GlyphInterface.toggleFrame = function(frameJSON, success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'toggleFrame', [frameJSON]
    );
};

GlyphInterface.turnOff = function(success, failure) {
    exec(
        success,
        failure,
        'GlyphPlugin',
        'turnOff'
    );
};

module.exports = GlyphInterface;
