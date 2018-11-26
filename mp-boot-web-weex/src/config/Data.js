/**
 * Created by sky.song on 2018/06/26
 */
import {getImagePath} from './Config'

export default {
  payWays: [
    {
      icon: getImagePath('purchase_by_weixin', '.png'),
      desc: '微信支付',
      statusIcon: getImagePath('choose', '.png'),
      isActive: true,
      payKey: 13
    },
    {
      icon: getImagePath('purchase_by_alipay', '.png'),
      desc: '支付宝支付',
      statusIcon: getImagePath('not_choose', '.png'),
      isActive: false,
      payKey: 14
    }
  ],
  defaultCashPlans:[
      {
          'id': -100172,
          'name': '7天',
          'salePrice': 6.3,
          'description': '每天仅需0.9元',
          'minute': 10080,
          'discount': 9,
          'promotionName': '赠送250豆',
          'tags': '',
          'checked': false,
          'medalName': null,
          'paymentType': 1,
          'card': null,
          'cardRecommendMessage': null
      },
      {
          'id': -100208,
          'name': '5天',
          'salePrice': 4.5,
          'description': '每天仅需0.9元',
          'minute': 7200,
          'discount': 9,
          'promotionName': '赠送250豆',
          'tags': '推荐',
          'checked': true,
          'medalName': null,
          'paymentType': 1,
          'card': null,
          'cardRecommendMessage': null
      },
      {
          'id': -31,
          'name': '90天',
          'salePrice': 81,
          'description': '每天仅需0.9元',
          'minute': 129600,
          'discount': 9,
          'promotionName': '',
          'tags': '',
          'checked': false,
          'medalName': null,
          'paymentType': 1,
          'card': null,
          'cardRecommendMessage': null
      }
  ],
    defaultBeanPlans:[
        {
            'id': -100172,
            'name': '7天',
            'salePrice': 700,
            'description': '每天仅需100灵豆',
            'minute': 10080,
            'discount': 9,
            'promotionName': null,
            'tags': '',
            'checked': false,
            'medalName': null,
            'paymentType': 2,
            'card': null,
            'cardRecommendMessage': null
        }
    ]
}