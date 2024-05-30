module.exports = {
    stories: ['../public/js/stories/**/*_stories.js'],
    addons: ["@storybook/addon-essentials"],
    core: {
        builder: 'webpack5',
    }
};
