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

module.exports = GlyphInterface;
