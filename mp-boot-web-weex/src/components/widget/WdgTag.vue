<!-- 标签 -->
<template>
    <div :class="['wdg-tag', 'border-' + type]"
         :style="newTheme.divStyle">
        <text :class="['tag-text', type]"
              :style="newTheme.textStyle">{{text}}
        </text>
    </div>
</template>

<script>
  export default {
    props: {
      text: {
        type: [String, Number],
        default: ''
      },
      type: {
        type: String,
        default: 'blue'
      },
      tagStyle: {
        type: Object,
        default: () => ({})
      }
    },
    computed: {
      newTheme() {
        const tagStyle = this.tagStyle;
        const tagText = this.text;
        let divStyle = {};
        let textStyle = {};
        // 字体大小
        if (tagStyle && tagStyle.fontSize) {
          textStyle = {
            ...textStyle,
            fontSize: `${tagStyle.fontSize}px`
          }
        }
        // 文字颜色
        if (tagStyle && tagStyle.color) {
          textStyle = {
            ...textStyle,
            color: tagStyle.color
          }
        }
        // 边框颜色
        if (tagStyle && tagStyle.borderColor) {
          divStyle = {
            ...divStyle,
            borderColor: tagStyle.borderColor
          }
        }
        // 边框粗细
        if (tagStyle && tagStyle.borderWidth) {
          divStyle = {
            ...divStyle,
            borderWidth: `${tagStyle.borderWidth}px`
          }
        }
        // 圆角幅度
        if (tagStyle && tagStyle.borderRadius) {
          divStyle = {
            ...divStyle,
            borderRadius: `${tagStyle.borderRadius}px`
          }
        }
        // 背景颜色
        if (tagStyle && tagStyle.backgroundColor) {
          divStyle = {
            ...divStyle,
            backgroundColor: tagStyle.backgroundColor
          }
        }
        // 高度
        if (tagStyle && tagStyle.height) {
          divStyle = {
            ...divStyle,
            height: `${tagStyle.height}px`
          };
        }
        // 宽度
        if (tagStyle && tagStyle.width) {
          divStyle = {
            ...divStyle,
            width: `${tagStyle.width}px`
          };
        }
        // margin
        if (tagStyle && tagStyle.marginTop) {
          divStyle = {
            ...divStyle,
            marginTop: `${tagStyle.marginTop}px`
          };
        }
        if (tagStyle && tagStyle.marginBottom) {
          divStyle = {
            ...divStyle,
            marginBottom: `${tagStyle.marginBottom}px`
          };
        }
        if (tagStyle && tagStyle.marginLeft) {
          divStyle = {
            ...divStyle,
            marginLeft: `${tagStyle.marginLeft}px`
          };
        }
        if (tagStyle && tagStyle.marginRight) {
          divStyle = {
            ...divStyle,
            marginRight: `${tagStyle.marginRight}px`
          };
        }
        if (tagText && tagText.length === 1) {
          divStyle = {
            ...divStyle,
            paddingLeft: 0,
            paddingRight: 0
          };
        }

        return {
          divStyle,
          textStyle
        };
      }
    }
  }
</script>

<style scoped>
    .wdg-tag {
        border-color: #3d3d3d;
        border-width: 2px;
        border-radius: 4px;
        margin-right: 6px;
        background-color: transparent;
        padding-left: 6px;
        padding-right: 6px;
        height: 26px;
        justify-content: space-between;
        align-items: center;
    }

    .tag-text {
        font-size: 20px;
        color: #3d3d3d;
    }

    .black {
        color: #3D3D3D;
    }

    .yellow {
        color: #EE9900;
    }

    .blue {
        color: #30A0FF;
    }

    .gray {
        color: #A5A5A5;
    }

    .red {
        color: #FF5000;
    }

    .border-black {
        border-color: #A5A5A5;
    }

    .border-yellow {
        border-color: #EE9900;
    }

    .border-blue {
        border-color: #30A0FF;
    }

    .border-gray {
        border-color: #A5A5A5;
    }

    .border-red {
        border-color: #FF5000;
    }
</style>